import CartaoArtigo from 'components/CartaoArtigo'
import Footer from 'components/Footer'
import Header from 'components/Header'

const Index = () =>{
  return(
    <>
      <Header ativo='artigo'/>
      <CartaoArtigo />
      <Footer />
    </>
  )
}

export default Index