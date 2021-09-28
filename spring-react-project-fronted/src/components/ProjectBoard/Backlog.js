import ProjectTask from "./ProjectTasks/ProjectTask";

const Backlog = (props) => {
  const project_tasks = props.project_tasks;

  //   console.log("backlogs inside the Backlog compponent", project_tasks);

  const tasks = project_tasks.map((project_task) => (
    <ProjectTask key={project_task.id} project_task={project_task} />
  ));

  let toDoTasks = [];
  let inProgressTasks = [];
  let doneTasks = [];

  for (let i = 0; i < tasks.length; i++) {
    console.log(tasks[i]);
    if (tasks[i].props.project_task.status === "TO_DO") {
      toDoTasks.push(tasks[i]);
    }
    if (tasks[i].props.project_task.status === "IN_PROGRESS") {
      inProgressTasks.push(tasks[i]);
    }
    if (tasks[i].props.project_task.status === "DONE") {
      doneTasks.push(tasks[i]);
    }
  }

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-4">
          <div className="card text-center mb-2">
            <div className="card-header bg-secondary text-white">
              <h3>TO DO</h3>
            </div>
          </div>

          {toDoTasks}
        </div>
        <div className="col-md-4">
          <div className="card text-center mb-2">
            <div className="card-header bg-primary text-white">
              <h3>In Progress</h3>
            </div>
          </div>
          {inProgressTasks}
        </div>
        <div className="col-md-4">
          <div className="card text-center mb-2">
            <div className="card-header bg-success text-white">
              <h3>Done</h3>
            </div>
          </div>
          {doneTasks}
        </div>
      </div>
    </div>
  );
};

export default Backlog;
