 
import { createRouter, createWebHistory } from 'vue-router'

import SBody from "../components/SBody"


const routes = [
    { 
        path: '/', 
        redirect: "/search"
        
    },
    { 
        path: '/search', 
        component: SBody 
    }
]
 
export default new createRouter({
  history: createWebHistory(),
  routes
})