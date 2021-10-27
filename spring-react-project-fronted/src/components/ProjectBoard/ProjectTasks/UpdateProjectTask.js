import { Link } from "react-router-dom";
import { useState, useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useHistory } from "react-router";
import classnames from "classnames";
import {
  getProjectTask,
  updateProjectTask,
} from "../../../actions/BacklogActions";

const UpdateProjectTask = (props) => {
  const { backlog_id, pt_id } = props.match.params;

  const dispatch = useDispatch();
  const history = useHistory();

  useEffect(async () => {
    await dispatch(getProjectTask(backlog_id, pt_id, history));
  }, []);

  const data = useSelector((state) => state.backlog.project_task);
  // const tempData = data;
  console.log("project Task for updating", data);

  const [projectTaskUpdate, setProjectTaskUpdate] = useState({
    summary: data.summary,
    acceptanceCriteria: data.acceptanceCriteria,
    dueDate: data.dueDate,
    priority: data.priority,
    status: data.status,
    projectIdentifer: data.projectIdentifer,
    projectSequence: data.projectSequence,
    id: data.id,
    create_At: data.create_At,
  });

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("after updation", projectTaskUpdate);
    dispatch(updateProjectTask(backlog_id, pt_id, projectTaskUpdate, history));
  };

  const errors = useSelector((state) => state.errors);

  return (
    <div className="add-PBI">
      <div className="container">
        <div className="row">
          <div className="col-md-8 m-auto">
            <Link to={`/projectBoard/${backlog_id}`} className="btn btn-light">
              Back to Project Board
            </Link>
            <h4 className="display-4 text-center">Update Project Task</h4>
            <p className="lead text-center">
              Project Name: {data.projectIdentifer} | Project Task Id:{" "}
              {data.projectSequence}
            </p>
            <form onSubmit={handleSubmit}>
              <div className="form-group">
                <input
                  type="text"
                  className={classnames("form-control form-control-lg ", {
                    "is-invalid": errors.summary,
                  })}
                  name="summary"
                  placeholder="Project Task summary"
                  value={projectTaskUpdate.summary}
                  onChange={(e) =>
                    setProjectTaskUpdate({
                      ...projectTaskUpdate,
                      summary: e.target.value,
                    })
                  }
                />
                {errors.summary && (
                  <div className="invalid-feedback">{errors.summary}</div>
                )}
              </div>
              <div className="form-group">
                <textarea
                  className="form-control form-control-lg"
                  placeholder="Acceptance Criteria"
                  name="acceptanceCriteria"
                  value={projectTaskUpdate.acceptanceCriteria}
                  onChange={(e) =>
                    setProjectTaskUpdate({
                      ...projectTaskUpdate,
                      acceptanceCriteria: e.target.value,
                    })
                  }
                ></textarea>
              </div>
              <h6>Due Date</h6>
              <div className="form-group">
                <input
                  type="date"
                  className="form-control form-control-lg"
                  name="dueDate"
                  value={projectTaskUpdate.dueDate}
                  onChange={(e) =>
                    setProjectTaskUpdate({
                      ...projectTaskUpdate,
                      dueDate: e.target.value,
                    })
                  }
                />
              </div>
              <div className="form-group">
                <select
                  className="form-control form-control-lg"
                  name="priority"
                  value={projectTaskUpdate.priority}
                  onChange={(e) =>
                    setProjectTaskUpdate({
                      ...projectTaskUpdate,
                      priority: e.target.value,
                    })
                  }
                >
                  <option value={0}>Select Priority</option>
                  <option value={1}>High</option>
                  <option value={2}>Medium</option>
                  <option value={3}>Low</option>
                </select>
              </div>

              <div className="form-group">
                <select
                  className="form-control form-control-lg"
                  name="status"
                  value={projectTaskUpdate.status}
                  onChange={(e) =>
                    setProjectTaskUpdate({
                      ...projectTaskUpdate,
                      status: e.target.value,
                    })
                  }
                >
                  <option value="">Select Status</option>
                  <option value="TO_DO">TO DO</option>
                  <option value="IN_PROGRESS">IN PROGRESS</option>
                  <option value="DONE">DONE</option>
                </select>
              </div>

              <input type="submit" className="btn btn-primary btn-block mt-4" />
            </form>
          </div>
        </div>
      </div>
    </div>
  );
};

export default UpdateProjectTask;
