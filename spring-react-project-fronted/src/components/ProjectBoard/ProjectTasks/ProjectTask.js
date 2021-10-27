import { Link } from "react-router-dom";
import { useDispatch } from "react-redux";
import { useHistory } from "react-router";
import { deleteProjectTask } from "../../../actions/BacklogActions";

const ProjectTask = (props) => {
  const task = props.project_task;
  let priorityClass;
  let priorityString;
  if (task.priority == 1) {
    priorityClass = "bg-danger text-light";
    priorityString = "High";
  } else if (task.priority == 2) {
    priorityClass = "bg-warning text-light";
    priorityString = "Medium";
  } else if (task.priority == 3) {
    priorityClass = "bg-info text-light";
    priorityString = "Low";
  }

  const dispatch = useDispatch();
  const history = useHistory();
  const deleteHandler = () => {
    dispatch(
      deleteProjectTask(task.projectIdentifer, task.projectSequence, history)
    );
  };

  return (
    <div className="card mb-1 bg-light">
      <div className={`card-header text-primary ${priorityClass}`}>
        ID: {task.projectSequence} -- Priority: {priorityString}
      </div>
      <div className="card-body bg-light">
        <h5 className="card-title">{task.summary}</h5>
        <p className="card-text text-truncate ">{task.acceptanceCriteria}</p>
        <Link
          to={`/updateProjectTask/${task.projectIdentifer}/${task.projectSequence}`}
          className="btn btn-primary"
        >
          View / Update
        </Link>

        <button className="btn btn-danger ml-4" onClick={deleteHandler}>
          Delete
        </button>
      </div>
    </div>
  );
};

export default ProjectTask;
