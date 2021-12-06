import { useState } from "react";
import { useHistory } from "react-router";
import { useDispatch, useSelector } from "react-redux";
import { CreateUser } from "../../actions/SecurityActions";
import classnames from "classnames";

const Signup = () => {
  const [user, setUser] = useState({
    fullName: "",
    username: "",
    password: "",
    confirmPassword: "",
    errors: {},
  });
  const history = useHistory();
  const dispatch = useDispatch();

  const { validToken } = useSelector((state) => state.security);
  if (validToken) {
    console.log("inside the Singup");
    history.push("/dashboard");
  }

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("user in signup component ", user);
    dispatch(CreateUser(history, user));
  };

  user.errors = useSelector((state) => state.errors);

  return (
    <div className="register">
      <div className="container">
        <div className="row">
          <div className="col-md-8 m-auto">
            <h1 className="display-4 text-center">Sign Up</h1>
            <p className="lead text-center">Create your Account</p>
            <form onSubmit={handleSubmit}>
              <div className="form-group">
                <input
                  type="text"
                  className={classnames("form-control form-control-lg ", {
                    "is-invalid": user.errors.fullName,
                  })}
                  placeholder="fullName"
                  name="fullname"
                  value={user.fullName}
                  onChange={(e) => {
                    setUser({
                      ...user,
                      fullName: e.target.value,
                    });
                  }}
                />
                {user.errors.fullName && (
                  <div className="invalid-feedback">{user.errors.fullName}</div>
                )}
              </div>
              <div className="form-group">
                <input
                  className={classnames("form-control form-control-lg ", {
                    "is-invalid": user.errors.username,
                  })}
                  placeholder="Email Address(Username)"
                  name="username"
                  value={user.username}
                  onChange={(e) => {
                    setUser({ ...user, username: e.target.value });
                  }}
                />

                {user.errors.username && (
                  <div className="invalid-feedback">{user.errors.username}</div>
                )}
              </div>

              <div className="form-group">
                <input
                  type="password"
                  className={classnames("form-control form-control-lg ", {
                    "is-invalid": user.errors.password,
                  })}
                  placeholder="Password"
                  name="password"
                  value={user.password}
                  onChange={(e) => {
                    setUser({ ...user, password: e.target.value });
                  }}
                />
                {user.errors.password && (
                  <div className="invalid-feedback">{user.errors.password}</div>
                )}
              </div>
              <div className="form-group">
                <input
                  type="password"
                  className={classnames("form-control form-control-lg ", {
                    "is-invalid": user.errors.confirmPassword,
                  })}
                  placeholder="Confirm Password"
                  name="confirmPassword"
                  value={user.confirmPassword}
                  onChange={(e) => {
                    setUser({ ...user, confirmPassword: e.target.value });
                  }}
                />
                {user.errors.confirmPassword && (
                  <div className="invalid-feedback">
                    {user.errors.confirmPassword}
                  </div>
                )}
              </div>
              <input type="submit" className="btn btn-info btn-block mt-4" />
            </form>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Signup;
