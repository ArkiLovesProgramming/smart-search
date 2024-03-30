<template lang="">
    <div>
        <el-menu
            :default-active="activeIndex"
            class="el-menu-demo"
            mode="horizontal"
            :ellipsis="false"
        >
            <el-menu-item index="0" class="logo">
                <el-text tag="b" type="primary" size="large">Smart Search</el-text>
            </el-menu-item>
            <!-- <el-input
                v-model="search_input_content"
                class="s_search_input"
                placeholder="Please Input"
                :suffix-icon="Search"
                @keydown="confirm_seach"
            /> -->
            <el-autocomplete
                v-model="search_input_content"
                :suffix-icon="Search"
                :fetch-suggestions="querySearchAsync"
                placeholder="Please input"
                @select="handleSelect"
                @keydown="confirm_seach"
                class="s_search_input"
            />
            <div class="flex-grow" />
            <el-menu-item index="1">Processing Center</el-menu-item>
            <el-sub-menu index="2" class="my_avatar">
                <template #title>
                    <!-- <el-avatar
                        src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
                    /> -->
                    <el-avatar v-if="!isLogined">guest</el-avatar>
                    <el-avatar v-else>{{user.username}}</el-avatar>
                </template>
                <el-menu-item index="2-1">Log out</el-menu-item>
                <el-menu-item index="2-2">Log in</el-menu-item>
                <el-menu-item index="2-3">Register</el-menu-item>
            </el-sub-menu>
        </el-menu>
    </div>
</template>
<script>
    import {reactive, ref} from 'vue'
    import { Search } from '@element-plus/icons-vue'
    import eventBus from '../lib/eventBus.js'
import api from '@/common/api.js'
    export default {
        name: "SHeader",
        setup(){
            let activeIndex = ref('1')

            let isLogined = ref();
            if (localStorage.getItem("user") != undefined){
                isLogined = ref(true);
            } else {
                isLogined = ref(false);
            }
            
            let user = reactive({});
            if (JSON.parse(localStorage.getItem("user"))!=undefined){
                user = JSON.parse(localStorage.getItem("user"))
            }

            let header_login = (user1)=>{
                user.username = user1.username;
                if (!isLogined.value){
                    isLogined.value = true
                }
            }

            eventBus.on("header_login", header_login);


            let search_input_content = ref('')

            let confirm_seach = (e)=>{
                if(e.keyCode==32){
                    search_input_content.value += " "
                }
                if(e.keyCode==13) { //用户点击了ctrl+enter触发
                    eventBus.emit("watch_input_data", {input: e.target.value, fromPage: 0});
                    eventBus.emit("detect_sentiment", e.target.value);
                }
            }

            let guess_results = ref([]);

            const querySearchAsync = (queryString, cb) => {
                if (queryString == "" || queryString == null){
                    return [];
                }
                console.log(queryString)
                api.search_keyword_title(queryString).then(
                    response=>{   
                        let items = []
                        for (let i of response.data.resultItems){
                            items.push({value: i.title})
                        }
                        console.log(items)
                        guess_results.value = items;
                        cb(guess_results.value);
                    },
                    error=>{
                        console.log(error)
                    }
                )
            };

            let handleSelect = ()=>{

            }

            return {
                activeIndex,
                search_input_content,
                Search,
                confirm_seach,
                user,
                isLogined,
                querySearchAsync,
                handleSelect
            }
        }
    }
</script>
<style lang="css" scoped>
    .flex-grow {
        flex-grow: 1;
    }
    .s_search_input{
        width: 400px!important;
        height: 44px!important;
        margin: auto 0px!important;
    }

    li.logo.is-active, li.my_avatar.is-active{
        background-color: inherit!important;
        border-bottom: 2px solid white;
    }
    li.logo.is-active{
        background-color: inherit!important;
        border-bottom: 2px solid white;
    }
    .el-menu--horizontal .el-menu-item:not(.is-disabled):focus, .el-menu--horizontal .el-menu-item:not(.is-disabled):hover{
        color: #409EFF;
    }
    .el-menu--horizontal .el-menu-item:not(.is-disabled):focus, .el-menu--horizontal .el-menu-item.logo:not(.is-disabled):hover{
        background-color: inherit;
        color: #409EFF;
    }
    .el-autocomplete .el-input{
        height: inherit!important;
    }
</style>