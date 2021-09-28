import { Link } from "react-router-dom";
import Backlog from "./Backlog";
import { useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import { useHistory } from "react-router";
import { getBacklogs } from "../../actions/BacklogActions";
const ProjectBoard = (props) => {
  const { id } = props.match.params;

  const history = useHistory();
  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(getBacklogs(id, history));
    // console.log("chaitanya ne dlwaaya");
  }, []);

  const backlogs = useSelector((state) => state.backlog.project_tasks);
  console.log("backlogs are", backlogs); //2 times rendering , :

  return (
    <div className="container">
      <Link to={`/addProjecTask/${id}`} className="btn btn-primary mb-3">
        <i className="fas fa-plus-circle"> Create Project Task</i>
      </Link>
      <br />
      <hr />

      <Backlog project_tasks={backlogs} />
    </div>
  );
};

export default ProjectBoard;
