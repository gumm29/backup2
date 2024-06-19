import Login from 'components/Login'
import Footer from 'components/Footer'
import Header from 'components/Header'

const LoginPage = () =>{
  return(
    <>
      <Header ativo='login'/>
      <Login/>
      <Footer/>
    </>
  )
}

export default LoginPage