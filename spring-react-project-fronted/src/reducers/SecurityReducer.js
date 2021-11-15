import { SET_CURRENT_USER } from "../actions/Types";

const initialState = {
  user: {},
  validToken: false,
};

const actionPayload = (payload) => {
  if (payload) {
    return true;
  } else {
    return false;
  }
};
export default function (state = initialState, action) {
  switch (action.type) {
    case SET_CURRENT_USER:
      return {
        ...state,
        validToken: actionPayload(action.payload),
        user: action.payload,
      };

    default:
      return state;
  }
}
