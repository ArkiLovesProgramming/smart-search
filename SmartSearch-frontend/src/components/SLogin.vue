<template>
    <div>
        <el-tabs v-model="activeName" class="demo-tabs" stretch="true" @tab-click="handleClick">
            <el-tab-pane label="Login" name="Login">
                <div class="login">
                    <el-text class="title" size="large" tag="b" >Login</el-text>
                    <el-form :model="form" label-width="auto" style="max-width: 600px">
                        <el-form-item label="">
                            <el-input
                                v-model="loginform.username"
                                style="width: 100%"
                                placeholder="Please input username"
                                clearable
                                prefix-icon
                            >
                                <template #prefix>
                                    <el-icon><User /></el-icon>
                                </template>
                            </el-input>
                        </el-form-item>
                        <el-form-item label="">
                            <el-input
                                v-model="loginform.password"
                                style="width: 100%"
                                type="password"
                                placeholder="Please input password"
                                show-password
                                clearable
                            >
                                <template #prefix>
                                    <el-icon><Lock /></el-icon>
                                </template>
                            </el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="loginclick">Login</el-button>
                        </el-form-item>
                    </el-form>
                </div>
            </el-tab-pane>
            <el-tab-pane label="Sign up" name="signup">
                <div class="Sign up">
                    <el-text class="title" size="large" tag="b" >Sign up</el-text>
                    <el-form :model="form" label-width="auto" style="max-width: 600px">
                        <el-form-item label="">
                            <el-input
                                v-model="signupform.username"
                                style="width: 100%"
                                placeholder="Please input username"
                                clearable
                                prefix-icon
                            >
                                <template #prefix>
                                    <el-icon><User /></el-icon>
                                </template>
                            </el-input>
                        </el-form-item>
                        <el-form-item label="">
                            <el-input
                                v-model="signupform.password"
                                style="width: 100%"
                                type="password"
                                placeholder="Please input password"
                                show-password
                                clearable
                            >
                                <template #prefix>
                                    <el-icon><Lock /></el-icon>
                                </template>
                            </el-input>
                        </el-form-item>
                        <el-form-item label="">
                            <el-input
                                v-model="signupform.repassword"
                                style="width: 100%"
                                type="password"
                                placeholder="Please input password"
                                show-password
                                clearable
                            >
                                <template #prefix>
                                    <el-icon><Lock /></el-icon>
                                </template>
                            </el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="signupclick">Sign up</el-button>
                        </el-form-item>
                    </el-form>
                </div>
            </el-tab-pane>
        </el-tabs>
        <div>
            
        </div>
    </div>
</template>
<script>
import { ref, reactive } from "vue";
import api from "@/common/api";
import { ElMessage } from 'element-plus';
import eventBus from "../lib/eventBus"
export default {
    name: "SLogin",
    setup(){
        let activeName = ref("Login")
        let loginform = reactive({});
        let signupform = reactive({});
        let loginButton = (e) => {
            console.log(e)
        }

        let login = ({username, password}) => {
            api.login(username, password).then(
                response=>{
                    console.log(response.data)
                    if (response.data.errorcode == 1){
                        msg2()
                    } else {
                        msg()
                        loginform.username = ""
                        loginform.password = ""
                        let user = {username: response.data.username}
                        localStorage.setItem("token", response.data.token);
                        localStorage.setItem("user", JSON.stringify(user));
                        eventBus.emit("header_login", user)
                        eventBus.emit("updateHistories")
                    } 
                },
                error=>{
                    console.log(error)
                }
            )
        }

        let loginclick = ()=>{
            login(loginform); //解构赋值..... 别忘了
        }

        let msg = () => {
            ElMessage({
                message: 'Login successfully',
                type: 'success',
            })
        }
        let msg2 = () => {
            ElMessage({
                message: 'Incorrect username or password',
                type: 'error',
            })
        }
        let msg3 = () => {
            ElMessage({
                message: 'Sign successfully',
                type: 'success',
            })
        }
        let msg4 = () => {
            ElMessage({
                message: 'account exists!',
                type: 'error',
            })
        }

        let signup = ({username, password})=>{
            api.create_user(username, password).then(
                response=>{
                    console.log(response.data)
                    signupform.username = ""
                    signupform.password = ""
                    if (response.data.code == 1){
                        msg4()
                    } else if (response.data.code == 0) {
                        msg3()
                    } 
                },
                error=>{
                    console.log(error)
                }
            )
        }

        let signupclick = ()=>{
            if (signupform.password != signupform.repassword){
                ElMessage({
                    message: 'The second password is different from the first one',
                    type: 'error',
                })
            } else {
                signup(signupform); //解构赋值..... 别忘了
            }
        }

        return {
            loginform,
            signupform,
            loginButton,
            activeName,
            loginclick,
            signupclick
        }
    }
}
</script>
<style lang="css">
    
</style>