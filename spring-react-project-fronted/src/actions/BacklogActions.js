import axios from "axios";
import {
  DELETE_PROJECT_TASK,
  GET_BACKLOG,
  GET_ERRORS,
  GET_PROJECT_TASK,
} from "./Types";

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
  try {
    const res = await axios.get(
      `http://localhost:8080/api/backlog/${backlog_id}`
    );

    // console.log("result from getBacklog actions", res);

    dispatch({
      type: GET_BACKLOG,
      payload: res.data,
    });
  } catch (error) {
    dispatch({
      type: GET_ERRORS,
      payload: error.response.data,
    });
  }

  // history.push(`/projectBoard/${backlog_id}`);
};

export const getProjectTask =
  (backlog_id, pt_id, history) => async (dispatch) => {
    try {
      const res = await axios.get(
        `http://localhost:8080/api/backlog/${backlog_id}/${pt_id}`
      );
      console.log("res inside the actions", res);

      dispatch({
        type: GET_PROJECT_TASK,
        payload: res.data,
      });
    } catch (error) {
      history.push("/dashboard");
    }
  };

export const updateProjectTask =
  (backlog_id, pt_id, updatedProjectTask, history) => async (dispatch) => {
    try {
      const res = await axios.patch(
        `http://localhost:8080/api/backlog/${backlog_id}/${pt_id}`,
        updatedProjectTask
      );
      console.log("res inside the actions for updated projectTask", res);

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

export const deleteProjectTask =
  (backlog_id, pt_id, history) => async (dispatch) => {
    if (
      window.confirm(
        `You are deleting project task ${pt_id}, this action cannot be undone`
      )
    ) {
      const res = await axios.delete(
        `http://localhost:8080/api/backlog/${backlog_id}/${pt_id}`
      );

      history.push(`/projectBoard/${backlog_id}`);

      dispatch({
        type: DELETE_PROJECT_TASK,
        payload: pt_id,
      });
    }
  };
