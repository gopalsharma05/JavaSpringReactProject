import "./App.css";
import Dashboard from "./components/Dashboard";
import "bootstrap/dist/css/bootstrap.min.css";
import Header from "./components/Layout/Header";
import { BrowserRouter as Router, Route } from "react-router-dom";
import AddProject from "./components/Project/AddProject";
import { Provider } from "react-redux";
import store from "./Store";
import UpdateProject from "./components/Project/UpdateProject";
import ProjectBoard from "./components/ProjectBoard/ProjectBoard";
import AddProjectTask from "./components/ProjectBoard/ProjectTasks/AddProjectTask";
import UpdateProjectTask from "./components/ProjectBoard/ProjectTasks/UpdateProjectTask";
import Landing from "./components/Layout/Landing";
import Login from "./components/UserManagement/Login";
import Signup from "./components/UserManagement/Signup";
import jwtDecode from "jwt-decode";
import setJWTToken from "./SecurityUtils/setJWTToken";
import { GET_ERRORS } from "./actions/Types";
import { logout } from "./actions/SecurityActions";

const jwtToken = localStorage.getItem("jwtToken");
if (jwtToken) {
  setJWTToken(jwtToken);
  const decoded = jwtDecode(jwtToken);
  store.dispatch({
    type: GET_ERRORS,
    payload: decoded,
  });

  const currentTime = Date.now() / 1000; //for miliseconds
  if (decoded.exp < currentTime) {
    store.dispatch(logout());
    window.location.href = "/";
  }
}

function App() {
  return (
    <Provider store={store}>
      <Router>
        <div className="App">
          <Header />

          {
            //public routes
          }

          <Route exact path="/" component={Landing} />
          <Route exact path="/register" component={Signup} />
          <Route exact path="/login" component={Login} />

          {
            //private routes
          }
          <Route exact path="/dashboard" component={Dashboard} />
          <Route exact path="/addProject" component={AddProject} />
          <Route exact path="/updateProject/:id" component={UpdateProject} />
          <Route exact path="/projectBoard/:id" component={ProjectBoard} />
          <Route exact path="/addProjecTask/:id" component={AddProjectTask} />
          <Route
            exact
            path="/updateProjectTask/:backlog_id/:pt_id"
            component={UpdateProjectTask}
          />
        </div>
      </Router>
    </Provider>
  );
}

export default App;
