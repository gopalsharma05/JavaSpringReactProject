import React, { Component } from "react";
import CreateProjectButton from "./Project/CreateProjectButton";
import ProjectItem from "./Project/ProjectItem";
import { connect } from "react-redux";
import { GetProjects } from "../actions/ProjectActions";
import PropTypes from "prop-types";

class Dashboard extends Component {
  componentDidMount() {
    this.props.GetProjects();
  }

  render() {
    return (
      <div className="projects">
        <div className="container">
          <div className="row">
            <div className="col-md-12">
              <h1 className="display-4 text-center">Projects</h1>
              <br />

              <CreateProjectButton />

              <br />
              <br />

              <ProjectItem />
            </div>
          </div>
        </div>
      </div>
    );
  }
}

const mapStateToProps = (state) => ({
  project: state.project,
});

Dashboard.propTypes = {
  project: PropTypes.object.isRequired,
  GetProjects: PropTypes.func.isRequired,
};

export default connect(mapStateToProps, { GetProjects })(Dashboard);
