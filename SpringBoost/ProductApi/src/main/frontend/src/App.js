import './App.css';
import React, {useState, useEffect} from "react";
import axios from "axios";
import Footer from "../include/footer";
import Heads from "../include/head";
import Navbar from "../include/navbar";
import Script from "../include/script";
import Sidebar from "../include/sidebar";

const Product = () => {
  const [product, setProduct] = useState([]);

  const fetchProduct = () => {
    axios.get("http://localhost:8066/api/v1/products").then(res => {
      console.log(res);
      setProduct(res.data);
    });
  };

  useEffect(() => {
    fetchProduct();
  }, []);

  return product.map((product, index) => {
    return(
        <div className="content-wrapper" key={index}>

          <section className="content-header">
            <div className="container-fluid">
              <div className="row mb-2">
                <div className="col-sm-6">
                  <h1>CarList</h1>
                </div>
                <div className="col-sm-6">
                  <ol className="breadcrumb float-sm-right">
                    <li className="breadcrumb-item"><a href="#">Home</a></li>
                    <li className="breadcrumb-item active">Product list</li>
                  </ol>
                </div>
              </div>
            </div>
          </section>

          <section className="content">
            <div className="container-fluid">
              <div className="row">
                <div className="col-12">
                  <div className="card">
                    <div className="card-header">
                      <h3 className="card-title">CarList</h3>
                    </div>
                    <div className="card-body">
                      <table id="example1" className="table table-bordered table-striped">
                        <thead>
                        <tr>
                          <th>ID</th>
                          <th>NAME</th>
                          <th>QTY</th>
                          <th>DESCRIBE</th>
                          <th>PRICE</th>
                          <th>ACTION</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                          <td>{product.proId}</td>
                          <td>{product.proName}</td>
                          <td>{product.proQty}</td>
                          <td>{product.proDescribe}</td>
                          <td>{product.proPrice}</td>
                          <td></td>
                        </tr>
                        </tbody>
                      </table>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </section>
        </div>
    )
  })
}
function App() {
  return (
      <html lang="en">
        <Heads/>
      <body>
      <div className="wrapper">
        <Navbar/>
        <Sidebar/>
        <Product/>
        <Footer/>
      </div>
      <Script/>
      </body>
      </html>
  );
}

export default App;
