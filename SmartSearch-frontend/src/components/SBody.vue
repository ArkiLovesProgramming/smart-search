<template lang="">
    <div>
        <el-backtop style="z-index: 1000;" :right="100" :bottom="100" />
        <el-text v-if="result_num != null">{{result_num}} results found</el-text>
        <SResultItem v-for="item in items" :item="item"/>
        <el-empty style="margin-top: 46px" :image-size="200"/>
        <SPage v-if="result_num != null" :result_num_numeric="result_num_numeric" :input_data="input_data"/>
    </div>
</template>
<script>
import SPage from './SPage.vue';
import SResultItem from './SResultItem.vue';
import api from "@/common/api";
import eventBus from "../lib/eventBus"
import { onMounted, reactive, ref } from 'vue';
export default {
    name: "SBody",
    components: {
        SResultItem,
        SPage
    },
    setup(){
        let result_num = ref();
        let result_num_numeric = ref(0)
        let input_data = ref("")
        let items = ref([]);
        let watch_input_data = ({input, fromPage}) => {
            input_data.value = input
            api.search_keyword(input, fromPage).then(
                response=>{
                    items.value = response.data.resultItems;
                    let str = response.data.totalNum.value;
                    result_num_numeric.value = str
                    result_num.value = (''+str).replace(/\$\s?|(,*)/g, '');
                    eventBus.emit("updateHistories")
                },
                error=>{
                    console.log(error)
                }
            )
        }

        // let watch_input_data = input => {
        //     input_data.value = input;
        //     // 模拟API响应
        //     let response = {
        //         data: {
        //             resultItems: [
        //             {
        //                 "favicon": "https://example3.com/favicon.ico",
        //                 "hostname": "example3.com",
        //                 "date": "2024-02-09",
        //                 "title": "How to improve Java code performance?",
        //                 "questionDesc": "My Java application is running slow, how can I improve its performance?"
        //             },
        //             {
        //                 "favicon": "https://example7.com/favicon.ico",
        //                 "hostname": "example7.com",
        //                 "date": "2024-02-13",
        //                 "title": "Java 8 Stream API best practices?",
        //                 "questionDesc": "How can I make the best use of Java 8 Stream API?"
        //             },
        //             {
        //                 "favicon": "https://example8.com/favicon.ico",
        //                 "hostname": "example8.com",
        //                 "date": "2024-02-14",
        //                 "title": "Microservices with Spring Boot in Java?",
        //                 "questionDesc": "What are the best practices for developing microservices with Spring Boot?"
        //             },
        //             {
        //                 "favicon": "https://example9.com/favicon.ico",
        //                 "hostname": "example9.com",
        //                 "date": "2024-02-15",
        //                 "title": "Securing Java web applications?",
        //                 "questionDesc": "What are the best strategies to secure Java web applications?"
        //             },
        //             {
        //                 "favicon": "https://example10.com/favicon.ico",
        //                 "hostname": "example10.com",
        //                 "date": "2024-02-16",
        //                 "title": "Implementing RESTful services in Java?",
        //                 "questionDesc": "How to implement efficient RESTful services in Java?"
        //             }
        //             ]
        //             ,
        //             totalNum: { value: 3 }
        //         }
        //     };
        //     items.value = response.data.resultItems;
        //     let str = response.data.totalNum.value;
        //     result_num_numeric.value = str;
        //     console.log(str);
        //     result_num.value = ('' + str).replace(/\$\s?|(,*)/g, '');
        // };

        onMounted(()=>{
            eventBus.on("watch_input_data", watch_input_data)
        })

        return {
            input_data,
            items,
            result_num,
            result_num_numeric
        }
    }
}
</script>
<style lang="">
    
</style>