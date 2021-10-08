<template>
    <div>
        <a-layout>
            <a-layout-content
                    :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
            >
                <p>
                    <a-form layout="inline" :model="param">
                        <a-form-item>
                            <a-input v-model="param.name" placeholder="书名">
                                <a-icon slot="prefix" type="book" style="color:rgba(0,0,0,.25)"/>
                            </a-input>
                        </a-form-item>
                        <a-form-item>
                            <a-button type="primary" @click="handleQuery({page:1,size:pagination.pageSize})">
                                查询
                            </a-button>
                        </a-form-item>
                        <a-form-item>
                            <a-button type="primary" @click="add">
                                新增
                            </a-button>
                        </a-form-item>
                    </a-form>
                </p>
                <a-table
                        :columns="columns"
                        :data-source="ebooks"
                        :row-key="record => record.id"
                        :pagination="pagination"
                        :loading="loading"
                        @change="handleTableChange"
                >
                    <template slot="cover" slot-scope="text, record">
                        <img class="ant-avatar" :src="record.cover" alt="avatar"/>
                    </template>
                    <template v-slot:action=" text, record ">
                        <a-space size="small">
                            <a-button type="primary" @click="edit(record)">
                                编辑
                            </a-button>
                            <a-popconfirm
                                    title="删除后不可恢复，确认删除吗？"
                                    ok-text="是"
                                    cancel-text="否"
                                    @confirm="handleDelete(record.id)"
                                    @cancel="cancel"
                            >
                                <a-button type="danger">
                                    删除
                                </a-button>
                            </a-popconfirm>

                        </a-space>
                    </template>
                </a-table>
            </a-layout-content>
        </a-layout>
        <a-modal
                title="电子书表单"
                :visible="modalVisible"
                :confirm-loading="confirmLoading"
                @ok="handleOk"
                @cancel="handleCancel"
        >
            <a-form :model="ebook" :label-col="{span : 6}" :wrapper-col="{ span: 18 }">
                <a-form-item label="封面">
                    <a-input v-model:value="ebook.cover"/>
                </a-form-item>
                <a-form-item label="名称">
                    <a-input v-model:value="ebook.name"/>
                </a-form-item>
                <a-form-item label="分类一">
                    <a-input v-model:value="ebook.category1Id"/>
                </a-form-item>
                <a-form-item label="分类二">
                    <a-input v-model:value="ebook.category2Id"/>
                </a-form-item>
                <a-form-item label="描述">
                    <a-input type="text" v-model:value="ebook.description"/>
                </a-form-item>
            </a-form>
        </a-modal>
    </div>
</template>

<script>
    import axios from "axios";
    import {message} from "ant-design-vue";

    export default {
        name: "AdminEbook",

        data() {
            return {
                pagination: {
                    current: 1,
                    pageSize: 4,
                    total: 0
                },
                ebooks: [],
                loading: false,
                columns: [
                    {
                        dataIndex: 'cover',
                        title: '封面',
                        scopedSlots: {customRender: 'cover'}
                    },
                    {
                        dataIndex: 'name',
                        title: '名称'
                    },
                    {
                        dataIndex: 'categoryId1',
                        title: '分类一',
                    },
                    {
                        dataIndex: 'categoryId2',
                        title: '分类二',
                    },
                    {
                        title: 'Action',
                        key: 'action',
                        scopedSlots: {customRender: 'action'},
                    },
                ],
                modalVisible: false,
                confirmLoading: false,
                ebook: {},
                param: {}
            }
        },

        methods: {
            handleQuery: function (params) {
                this.loading = true;
                const _this = this;
                axios.get("/ebook/list", {
                    //左边params固定的
                    params: {
                        page: params.page,
                        size: params.size,
                        name: _this.param.name
                    }
                }).then(resp => {
                    this.loading = false;
                    console.log("resp ====> ", resp)
                    if (resp.data.success) {
                        _this.ebooks = resp.data.content.list;
                        //重置分页按钮
                        _this.pagination.current = params.page;
                        _this.pagination.total = resp.data.content.total;
                    } else {
                        message.error(resp.data.message);
                    }
                }).catch(e => {
                    message.error(e.message);
                });
            },
            handleTableChange: function (pagination) {
                console.log("看看自带的分页参数：", pagination);
                this.handleQuery({
                    page: pagination.current,
                    size: pagination.pageSize
                });
            },
            // ---- 表单 ----
            edit: function (record) {
                this.modalVisible = true;
                console.log(record)
                this.ebook = record;
            },
            handleOk() {
                this.confirmLoading = true;
                console.log(this.ebook)
                axios.post("/ebook/save", this.ebook).then(response => {
                    const data = response.data;
                    if (data.success) {
                        this.modalVisible = false;
                        this.confirmLoading = false;
                        //重新加载列表
                        this.handleQuery({
                            //查询当前分页组件所在的页码
                            page: this.pagination.current,
                            size: this.pagination.pageSize
                        })
                    } else {
                        this.$message.error('数据修改失败');
                    }
                })
            },
            handleCancel(e) {
                console.log('Clicked cancel button');
                this.modalVisible = false;
            },
            cancel(e) {
                console.log(e);
                this.$message.error('你已经取消删除');
            },
            add: function () {
                this.modalVisible = true;
                this.ebook = {};
            },
            handleDelete(id) {
                axios.delete("/ebook/delete/" + id).then(response => {
                    const data = response.data; // data = commonResp
                    console.log("delete data ====>", data);
                    if (data.success) {
                        this.handleQuery({
                            page: this.pagination.current,
                            size: this.pagination.pageSize
                        })
                    }
                })
            }
        },

        created() {
            this.handleQuery({
                page: 1,
                size: this.pagination.pageSize
            });
        }
        ,

        mounted() {

        }
    }
</script>

<style scoped>
    .ant-avatar {
        width: 50px;
        height: 50px;
        line-height: 50px;
        border-radius: 8%;
        margin: 5px 0;
    }
</style>