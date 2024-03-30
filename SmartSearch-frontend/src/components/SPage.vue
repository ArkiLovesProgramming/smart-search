<template lang="">
    <div>
        <el-pagination background layout="prev, pager, next"  :page-size="20" :total="result_num_numeric"
            @current-change="handleCurrentChange"
        />
    </div>
</template>
<script>
import eventBus from '@/lib/eventBus';
import { watchEffect } from "vue";
export default {
    name: "SPage",
    props: ["result_num_numeric", "input_data"],
    setup(props){
        const handleCurrentChange = (val) => {
            eventBus.emit("watch_input_data", {input: props.input_data, fromPage: val-1});
        }

        return {
            handleCurrentChange
        }
    }
}
</script>
<style lang="">
    
</style>