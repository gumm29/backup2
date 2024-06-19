import Footer from 'components/Footer'
import Header from 'components/Header'
import FormularioArtigo from 'components/FormularioArtigo'

//pagina editar - modo='editar'
const Formulario = () =>{
  return(
    <>
      <Header ativo=''/>
      <FormularioArtigo modo='' /> 
      <Footer />
    </>
  )
}

export default Formulario