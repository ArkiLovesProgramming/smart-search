import axios from 'axios'

const HOST_NAME = "https://smartsearch.api.arkilovesprogramming.com/"

const searchApi = {

    BASE_URL: HOST_NAME + "api/",

    search_keyword(keyword, fromPage){
        let token = localStorage.getItem("token")
        let url = this.BASE_URL + `search/keyword/${keyword}`
        return ajax(url, 'get', { headers: {token: token}, params: {fromPage: fromPage}});
    },

    search_keyword_title(keyword){
      let url = this.BASE_URL + `search/keyword/title/${keyword}`
      return ajax(url, 'get');
    }
}

const nlpApi = {

  BASE_URL: HOST_NAME + "api/",

  detect_keyword(keyword){
      let url = this.BASE_URL + `nlp/detect/${keyword}`
      return ajax(url, 'get');
  }
}

const userApi = {

  BASE_URL: HOST_NAME + "api/",

  create_user(username, password){
      let url = this.BASE_URL + `user/signup`
      return ajax(url, 'post', {data: {username: username, password: password}});
  },

  login(username, password){
    let url = this.BASE_URL + `user/login`
    return ajax(url, 'post', {data: {username: username, password: password}});
  }
}

const historyApi = {

  BASE_URL: HOST_NAME + "api/",

  getHistroies(){
    let token = localStorage.getItem("token")
    let url = this.BASE_URL + `history/get`
    return ajax(url, 'get', {headers: {token: token}});
  }
}

let api = Object.assign(searchApi, nlpApi, userApi, historyApi)
export default api

function ajax(url, method, options) {
    if (options !== undefined) {
      var { params = {}, data = {}, headers = {} } = options
    } else {
      params = data = headers = {}
    }
    return new Promise((resolve, reject) => {
      axios({
        url,
        method,
        params,
        data,
        headers
      }).then((res) => {
        resolve(res)
      }).catch(error => {
        reject(error)
      })
    })
  }