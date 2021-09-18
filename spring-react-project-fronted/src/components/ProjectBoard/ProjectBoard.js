import { Link } from "react-router-dom";
import Backlog from "./Backlog";
const ProjectBoard = (props) => {
  const { id } = props.match.params;
  return (
    <div className="container">
      <Link to={`/addProjecTask/${id}`} className="btn btn-primary mb-3">
        <i className="fas fa-plus-circle"> Create Project Task</i>
      </Link>
      <br />
      <hr />

      <Backlog />
    </div>
  );
};

export default ProjectBoard;
