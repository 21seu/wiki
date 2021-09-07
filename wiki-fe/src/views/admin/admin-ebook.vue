<template>
    <div>
        <a-layout>
            <a-layout-content
                    :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
            >
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
                                <a-button type="danger" @click="handleCancel">
                                    删除
                                </a-button>
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
            <a-form :model="ebooks" :label-col="{span : 6}" :wrapper-col="{ span: 18 }">
                <a-form-item label="封面">
                    <a-input v-model:value="ebooks.cover" />
                </a-form-item>
                <a-form-item label="名称">
                    <a-input v-model:value="ebooks.name" />
                </a-form-item>
                <a-form-item label="分类一">
                    <a-input v-model:value="ebooks.category1Id" />
                </a-form-item>
                <a-form-item label="分类二">
                    <a-input v-model:value="ebooks.category2Id" />
                </a-form-item>
                <a-form-item label="描述">
                    <a-input type="text" v-model:value="ebooks.description" />
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
                confirmLoading: false
            }
        },

        methods: {
            handleQuery: function (params) {
                //this.loading = true;
                const _this = this;
                axios.get("/ebook/list", {
                    //左边params固定的
                    params: {
                        page: params.page,
                        size: params.size
                    }
                }).then(resp => {
                    console.log(resp.data.content);
                    _this.ebooks = resp.data.content.list;

                    //重置分页按钮
                    _this.pagination.current = params.page;
                    _this.pagination.total = resp.data.content.total;
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
                this.ebooks = record;
            },
            handleOk(e) {
                this.confirmLoading = true;
                setTimeout(() => {
                    this.modalVisible = false;
                    this.confirmLoading = false;
                }, 2000);
            },
            handleCancel(e) {
                console.log('Clicked cancel button');
                this.modalVisible = false;
            },
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