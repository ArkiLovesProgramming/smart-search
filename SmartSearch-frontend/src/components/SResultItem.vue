<template lang="">
    <div class="result_item">
        <el-card shadow="hover"> 
            <div class="d-flex align-content-center">
                <el-avatar class="me-2"
                    :src="item.favicon"
                />
                <span class="host_text_info_box"  >
                    <el-text line-clamp="1">
                    {{item.hostname}}
                    </el-text><br>
                    <el-text line-clamp="1">
                        {{item.date}}
                    </el-text>
                </span>
            </div>
            <el-text size="large" tag="b" type="primary">
                {{item.title}}
            </el-text>
            <div>
                <el-text style="color:#303133" line-clamp="7">
                    {{item.questionDesc}}
                </el-text>
            </div>
        </el-card>
    </div>
</template>
<script>
    export default {
        name: "SResultItem",
        props: ["item"],
        setup(props){
            // console.log(props.item)
        }
    }
</script>
<style lang="css" scoped>
    .result_item{
        margin: 10px 0px;
        cursor: pointer;
    }
    .host_text_info_box{
        line-height: 20px; 
        height: 40px; 
        width: 80%;
    }
    .host_text_info_box span{
        vertical-align: bottom;
    }
</style>