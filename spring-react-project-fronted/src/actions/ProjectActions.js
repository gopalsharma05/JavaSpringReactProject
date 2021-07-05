import axios from "axios";
import { GET_ERRORS, GET_PROJECTS, GET_PROJECT } from "./Types";

export const CreateProject = (project, history) => async (dispatch) => {
  try {
    const res = await axios.post("http://localhost:8080/api/project", project);
    history.push("/dashboard");
    dispatch({
      type: GET_ERRORS,
      payload: {}, // No error should be there in state, if project created successfully
    });
  } catch (err) {
    // console.log(err.response.data);  // to see the error else, if we do not use correct id then 400 error will come
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data,
    });
  }
};

export const GetProjects = () => async (dispatch) => {
  const res = await axios.get("http://localhost:8080/api/project/all");
  dispatch({
    type: GET_PROJECTS,
    payload: res.data,
  });
};

export const GetProject = (id, history) => async (dispatch) => {
  try {
    // if we want to pass the parameter with the url, use the below syntax
    const res = await axios.get(`http://localhost:8080/api/project/${id}`);
    dispatch({
      type: GET_PROJECT,
      payload: res.data,
    });
  } catch (error) {
    history.push("/dashboard");
  }
};
