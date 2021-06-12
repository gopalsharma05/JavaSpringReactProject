import axios from "axios";
import { GET_ERRORS } from "./Types";

export const CreateProject = (project, history) => async (dispatch) => {
  try {
    const res = await axios.post("http://localhost:8080/api/project", project);
    history.push("/dashboard");
  } catch (err) {
    // console.log(err.response.data);  // to see the error else, if we do not use correct id then 400 error will come
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data,
    });
  }
};
