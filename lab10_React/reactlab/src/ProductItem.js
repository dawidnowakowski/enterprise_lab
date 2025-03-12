import { Link } from "react-router-dom";

function ProductItem(props) {
  return (
    <li>
      <Link to={`details/${props.id}`}>{props.title}</Link> ({props.brand})
    </li>
  );
}

export default ProductItem;