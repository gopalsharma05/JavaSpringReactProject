import { combineReducers } from "redux";
import ErrorReducer from "./ErrorReducer";
import ProjectReducer from "./ProjectReducer";
import ProjectTasksReducer from "./ProjectTasksReducer";
import SecurityReducer from "./SecurityReducer";

export default combineReducers({
  errors: ErrorReducer,
  project: ProjectReducer,
  backlog: ProjectTasksReducer,
  security: SecurityReducer,
});
