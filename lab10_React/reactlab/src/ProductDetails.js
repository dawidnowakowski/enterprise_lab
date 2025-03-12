import React from "react";
import { useParams, Link } from "react-router-dom";

function ProductDetails({ products }) {
  const { id } = useParams();

  const filteredProducts = products.filter(
    (product) => product.id === parseInt(id)
  );

  if (filteredProducts.length === 0) {
    return null;
  }

  const product = filteredProducts[0];

  return (
    <div>
      <h1>{product.name}</h1>
      <p>Category: {product.category}</p>
      <p>Brand: {product.brand}</p>
      <p>Description: {product.description}</p>
      <p>Price: {product.price}</p>
      <br />
      <img src={product.thumbnail} alt="alt"/>
      <br />
      <Link to="/">Back to list</Link>
    </div>
  );
}

export default ProductDetails;
