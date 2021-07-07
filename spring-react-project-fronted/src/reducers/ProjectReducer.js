import { DELETE_PROJECT, GET_PROJECTS, GET_PROJECT } from "../actions/Types";

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

    case GET_PROJECT:
      return {
        ...state,
        project: action.payload,
      };

    case DELETE_PROJECT:
      return {
        ...state,
        projects: state.projects.filter(
          (project) => project.projectIdentifier !== action.payload
        ),
      };

    default:
      return state;
  }
}
