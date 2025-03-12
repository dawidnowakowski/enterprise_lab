import { useState } from "react";
import ProductItem from "./ProductItem";

function ProductList(props) {
  
  const [filter, setFilter] = useState('');
  
  return (
    <div>
      <h1>List of products</h1>
        <label for="filter">Filter by product title: </label><input id="filter" onChange={(event) => setFilter(event.target.value)}/>
      <ul>
        {props.products.filter((product) => product.title.includes(filter)).map((product) => (
          <ProductItem
            key={product.id}
            id={product.id}
            title={product.title}
            brand={product.brand}
          ></ProductItem>
        ))}
      </ul>
    </div>
  );
}

export default ProductList;
