import { GET_PROJECTS } from "../actions/Types";

const initialState = {
  projects: [], // to get all projects using an array
  project: {}, // will be used when we get project by id
};

export default function (state = initialState, action) {
  switch (action.type) {
    case GET_PROJECTS:
      return {
        ...state,
        projects: action.payload,
      };

    default:
      return state;
  }
}
