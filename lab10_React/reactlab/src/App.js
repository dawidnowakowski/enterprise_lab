import { useEffect, useState } from "react";
import "./App.css";
import ProductList from "./ProductList";
import ProductDetails from "./ProductDetails";
import axios from "axios";
import {

  createBrowserRouter,
  RouterProvider,
} from "react-router-dom";

function App() {
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    axios.get('https://dummyjson.com/products').then((response) => {
      setProducts(response.data.products);
      setLoading(false);
    });
  }, []);

  if (loading) {
    return <div>Loading...</div>;
  }

  const router = createBrowserRouter([
    {
      path: '/',
      element: <ProductList products={products} />,
    },
    {
      path: 'details/:id',
      element: <ProductDetails products={products} />,
    },
  ]);

  return <RouterProvider router={router} />;
}

export default App;
