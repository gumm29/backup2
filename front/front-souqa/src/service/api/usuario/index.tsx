import axios from 'axios'

const BASE_URL = process.env.REACT_APP_BACKEND_URL ?? 'http://localhost:8081'

export const login = (payload : object) =>{
  return axios.post(`${BASE_URL}/auth/cliente`, payload)
}

export const nomeUsuario = (email: string) =>{
  let token = localStorage.getItem('id') || ''
  return axios.get(`${BASE_URL}/autor/${email}`,{headers: {"Authorization": token}})
}