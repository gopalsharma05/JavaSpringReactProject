import axios from "axios";
import { GET_BACKLOG, GET_ERRORS, SET_CURRENT_USER } from "./Types";
import setJWTToken from "../SecurityUtils/setJWTToken";
import jwtDecode from "jwt-decode";

export const CreateUser = (history, user) => async (dispatch) => {
  try {
    console.log("user is ", user);
    const res = await axios.post(
      "http://localhost:8080/api/users/register",
      user
    );
    console.log("result for createUser from backend is ", res.data);
    dispatch({
      type: GET_ERRORS,
      payload: {}, // No error should be there in state, if user created successfully
    });

    history.push("/login");
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data,
    });
  }
};

export const LoginRequest = (history, loginData) => async (dispatch) => {
  try {
    const res = await axios.post(
      "http://localhost:8080/api/users/login",
      loginData
    );

    console.log("data from login action ", res.data);

    const { token } = res.data;

    localStorage.setItem("jwtToken", token);

    setJWTToken(token);

    const decoded = jwtDecode(token);

    dispatch({
      type: SET_CURRENT_USER,
      payload: decoded,
    });
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data,
    });
  }
};

export const logout = () => (dispatch) => {
  localStorage.removeItem("jwtToken");
  setJWTToken(false);
  dispatch({
    type: SET_CURRENT_USER,
    payload: {},
  });
};
