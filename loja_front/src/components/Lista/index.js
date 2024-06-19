import React, { useEffect, useState } from 'react';
import './styles.css'

const Lista = () => {
  const [data, setData] = useState(null);

  const formatarDecimal = (numero) => {
    return numero.toFixed(2).replace('.', ',');
  }

  useEffect(() => {
    fetchData();
  }, []);

  const fetchData = async () => {
    try {
      const response = await fetch('http://localhost:8081/produtos');
      const jsonData = await response.json();
      setData(jsonData.content);
    } catch (error) {
      console.error('Error fetching data:', error);
    }
  };

  return (
    <div id="container">
    <div class="grid">
      {data ? (
        <>
          {data.map(item => (
            <div key={item.id} class="produto">
                <img key={item.id} src={"data:image/png;base64,"+ item.imagemProd}/>
                <div key={item.id}><b>item:</b> {item.produto}</div>
                <div key={item.id}><b>descrição:</b> {item.descricao}</div>
                <div key={item.id}><b>preço de:</b> {formatarDecimal(item.precoDe)}</div>
                <div key={item.id}><b>preço:</b> {formatarDecimal(item.preco)}</div>
                <button>Ver produto</button>
            </div>
          ))}
        </>
      ) : (
        <p>Loading data...</p>
      )}
    </div>
    </div>
  );
};

export default Lista;
