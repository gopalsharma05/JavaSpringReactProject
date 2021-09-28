import axios from "axios";
import { GET_BACKLOG, GET_ERRORS } from "./Types";

export const addProjectTask =
  (backlog_id, projectTask, history) => async (dispatch) => {
    console.log("my data is ", projectTask);
    try {
      await axios.post(
        `http://localhost:8080/api/backlog/${backlog_id}`,
        projectTask
      );

      // console.log("result from addprojectTask acitions is ", res);

      history.push(`/projectBoard/${backlog_id}`);

      dispatch({
        type: GET_ERRORS,
        payload: {},
      });
    } catch (error) {
      dispatch({
        type: GET_ERRORS,
        payload: error.response.data,
      });
    }
  };

export const getBacklogs = (backlog_id, history) => async (dispatch) => {
  const res = await axios.get(
    `http://localhost:8080/api/backlog/${backlog_id}`
  );

  // console.log("result from getBacklog actions", res);

  dispatch({
    type: GET_BACKLOG,
    payload: res.data,
  });

  // history.push(`/projectBoard/${backlog_id}`);
};
