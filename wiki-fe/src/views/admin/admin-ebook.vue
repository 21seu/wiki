<template>
    <a-layout>
        <a-layout-content
                :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
        >
            <a-table
                    :columns="columns"
                    :data-source="data"
                    :row-key="record => record.id"
                    :pagination="pagination"
                    :loading="loading"
                    @change="handleTableChange"
            >
                <template #cover="{ text: cover }">
                    <img v-if="cover" :src="cover" alt="avatar"/>
                </template>
                <template v-slot:action="{ text: record }">
                    <a-space size="small">
                        <a-button type="primary">
                            编辑
                        </a-button>
                        <a-button type="danger">
                            删除
                        </a-button>
                    </a-space>
                </template>
            </a-table>
        </a-layout-content>
    </a-layout>
</template>

<script>
    export default {
        name: "AdminEbook"
    }
    const columns = [
        {
            dataIndex: 'cover',
            title: '封面',
            slots: {customRender: 'cover'}
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
    ];
</script>

<style scoped>

</style>