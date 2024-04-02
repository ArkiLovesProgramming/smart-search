<template lang="">
    <div class="sside">
        <el-card class="asside_comp" shadow="never">
            <SLogin/>
        </el-card>
        <el-card class="asside_comp" shadow="never">
            <el-text class="title" size="large" tag="b" >Sentiment</el-text>
            <div v-for="(item, index) in sentiment_sorted" :key="index">
                <el-text size="small">{{ item[0] }}</el-text>
                <el-progress :percentage="item[1]" />
            </div>
            <!-- <div>
                <el-text size="small">Positive</el-text>
                <el-progress :percentage="50" />
            </div>
            <div>
                <el-text size="small">Positive</el-text>
                <el-progress :percentage="50" />
            </div>
            <div>
                <el-text size="small">Positive</el-text>
                <el-progress :percentage="50" />
            </div> -->
        </el-card>

        <el-card class="asside_comp topics_box" shadow="never" v-if="false">
            <el-text class="title" size="large" tag="b" >Reletive Topic</el-text>
            <el-tag size="large" closable>Large</el-tag>
            <el-tag size="large" closable>Large La</el-tag>
            <el-tag size="large" closable>Lad</el-tag>
            <el-tag size="large" closable>Largsse</el-tag>
            <el-tag size="large" closable>Largae</el-tag>
            <el-tag size="large" closable>Lar</el-tag>
            <el-tag size="large" closable>Largedsadas</el-tag>
            <el-tag size="large" closable>Large</el-tag>
            <el-tag size="large" closable>Large La</el-tag>
            <el-tag size="large" closable>Lad</el-tag>
            <el-tag size="large" closable>Largsse</el-tag>
            <el-tag size="large" closable>Largae</el-tag>
            <el-tag size="large" closable>Lar</el-tag>
            <el-tag size="large" closable>Largedsadas</el-tag>
        </el-card>

        <el-card v-if="histories && histories.length > 0" class="asside_comp" shadow="never">
            <el-text class="title" size="large" tag="b" >History</el-text>
            <div class="history_item" v-for="item in histories" >
                {{item.keyword}}
            </div>
            <a href="#">...</a>
        </el-card>

    </div>
</template>
<script>
import api from "@/common/api";
import { ref, onMounted, computed } from 'vue';
import eventBus from "../lib/eventBus"
import SLogin from "./SLogin.vue"
export default {
    name: "SSide",
    components: {
        SLogin
    },
    setup() {
        let sentiment = ref({});
        let detect_sentiment = function(keyword){
            api.detect_keyword(keyword).then(
                response=>{
                    sentiment.value = response.data
                },
                error=>{
                    console.log(error)
                }
            )
        }
        let sentiment_sorted = computed(()=>{
            const dataArray = Object.entries(sentiment.value);
            for (var i = 0; i < dataArray.length; i++){
                dataArray[i][1] = parseFloat((dataArray[i][1] * 100).toFixed(2));
            }
            // 按照数组的顺序排序
            dataArray.sort((a, b) => {
            // 对比数组项的值
                return b[1] - a[1];
            });
            console.log(dataArray)
            return dataArray
        })

        onMounted(()=>{
            eventBus.on("detect_sentiment", detect_sentiment)
            eventBus.on("updateHistories", updateHistories)
            updateHistories();
        })

        let histories = ref([{}]);

        let updateHistories = ()=>{
            api.getHistroies().then(
                response=>{
                    if (response.data.length >= 2){
                        histories.value = [response.data[0], response.data[1]];
                    } else if (response.data.length == 1) {
                        histories.value = [response.data[0]];
                    } else {
                        
                    }
                    
                },
                error=>{
                    console.log(error)
                }
            )
        }

        return {
            sentiment_sorted,
            histories
        }
    }
}
</script>
<style lang="css">
    .sside{
        margin-top: 34px;
        /* padding: 10px; */
        /* background-color: aqua; */
        border-radius: 5px;
    }
    .sside .title{
        margin: 10px 0px;
        display: block;
    }
    .sside .topics_box > .el-card__body > span{
        margin-right: 10px;
        margin-bottom: 10px;
        cursor: pointer;
    }
    .asside_comp{
        margin-bottom: 10px;
    }

    .history_item{
        padding: 4px 0px;
        font-size: 12px;
        transition: 0.1s;
    }
    .history_item:hover{
        transform: scale(1.01);
        cursor: pointer;
        
    }
</style>