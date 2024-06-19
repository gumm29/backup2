import axios from 'axios'

const BASE_URL = process.env.REACT_APP_BACKEND_URL ?? 'http://localhost:8081'

export const nomeUsuario = (email: string) =>{
  let token = localStorage.getItem('id') || ''
  return axios.get(`${BASE_URL}/autor/${email}`, {headers: {'Authorization': token}})
}

export const listaArtigos = () =>{
  return axios.get(`${BASE_URL}/artigo`)
}

export const listaArtigo = (id: number) =>{
  return axios.get(`${BASE_URL}/artigo/${id}`)
}

export const criaArtigo = (payload: object) =>{
  let token = localStorage.getItem('id') || ''
  return axios.post(`${BASE_URL}/artigo`, payload, {headers: {'Authorization': token}})
}

export const alteraArtigo = (id: number, payload: object) =>{
  return axios.put(`/artigo/${id}`, payload)
}

export const deletaArtigo = (id: number) =>{
  axios.delete(`/artigo/${id}`)
}