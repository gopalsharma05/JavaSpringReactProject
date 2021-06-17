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
    const { projects } = this.props.project;
    // above is destructuring .. inside the this.props.project there is project //array so we can directly write it into
    //{ } to access it or  also we can store that into another variable like const var=this.props.project.projects

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

              {
                //as we want to write JS code inside the JSX, so need to use {}
                projects.map((project) => (
                  <ProjectItem key={project.id} project={project} />
                ))
              }
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
