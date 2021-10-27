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

  const project_tasks = useSelector((state) => state.backlog.project_tasks);
  const errors = useSelector((state) => state.errors);
  console.log("backlogs are", project_tasks); //2 times rendering , :

  let BoardContent;

  const boardAlgorithm = (errors, project_tasks) => {
    if (project_tasks.length < 1) {
      if (errors.projectNotFound) {
        return (
          <div className="alert alert-danger text-center" role="alert">
            {errors.projectNotFound}
          </div>
        );
      } else {
        return (
          <div className="alert alert-info text-center" role="alert">
            No Project Tasks on this board
          </div>
        );
      }
    } else {
      return <Backlog project_tasks={project_tasks} />;
    }
  };

  BoardContent = boardAlgorithm(errors, project_tasks);

  return (
    <div className="container">
      <Link to={`/addProjecTask/${id}`} className="btn btn-primary mb-3">
        <i className="fas fa-plus-circle"> Create Project Task</i>
      </Link>
      <br />
      <hr />

      {BoardContent}
    </div>
  );
};

export default ProjectBoard;
