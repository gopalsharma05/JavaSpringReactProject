import { Link, useHistory } from "react-router-dom";
import { useState } from "react";
import { useSelector, useDispatch } from "react-redux";
import PropTypes from "prop-types";
import { addProjectTask } from "../../../actions/BacklogActions";
import classnames from "classnames";

const AddProjectTask = (props) => {
  const { id } = props.match.params;

  addProjectTask.propTypes = {
    addProjectTask: PropTypes.func.isRequired,
  };

  const [projectTaskData, setProjectTaskData] = useState({
    summary: "",
    acceptanceCriteria: "",
    status: "",
    priority: 0,
    dueDate: "",
    projectIdentifier: id,
    errors: {},
  });

  const dispatch = useDispatch();
  const history = useHistory();
  projectTaskData.errors = useSelector((state) => state.errors);

  if (projectTaskData.errors == null)
    console.log("errors in projectTaskData is ", projectTaskData.errors);

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("projectTaskdata is ", projectTaskData);
    dispatch(addProjectTask(id, projectTaskData, history));
  };
  return (
    <div className="add-PBI">
      <div className="container">
        <div className="row">
          <div className="col-md-8 m-auto">
            <Link to={`/projectBoard/${id}`} className="btn btn-light">
              Back to Project Board
            </Link>
            <h4 className="display-4 text-center">Add Project Task</h4>
            <p className="lead text-center">Project Name + Project Code</p>
            <form onSubmit={handleSubmit}>
              <div className="form-group">
                <input
                  type="text"
                  className={classnames("form-control form-control-lg ", {
                    "is-invalid": projectTaskData.errors.summary,
                  })}
                  name="summary"
                  placeholder="Project Task summary"
                  value={projectTaskData.summary}
                  onChange={(e) =>
                    setProjectTaskData({
                      ...projectTaskData,
                      summary: e.target.value,
                    })
                  }
                />
                {projectTaskData.errors.summary && (
                  <div className="invalid-feedback">
                    {projectTaskData.errors.summary}
                  </div>
                )}
              </div>
              <div className="form-group">
                <textarea
                  className="form-control form-control-lg"
                  placeholder="Acceptance Criteria"
                  name="acceptanceCriteria"
                  value={projectTaskData.acceptanceCriteria}
                  onChange={(e) =>
                    setProjectTaskData({
                      ...projectTaskData,
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
                  value={projectTaskData.dueDate}
                  onChange={(e) => {
                    setProjectTaskData({
                      ...projectTaskData,
                      dueDate: e.target.value,
                    });
                  }}
                />
              </div>
              <div className="form-group">
                <select
                  className="form-control form-control-lg"
                  name="priority"
                  value={projectTaskData.priority}
                  onChange={(e) =>
                    setProjectTaskData({
                      ...projectTaskData,
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
                  value={projectTaskData.status}
                  onChange={(e) =>
                    setProjectTaskData({
                      ...projectTaskData,
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

export default AddProjectTask;
