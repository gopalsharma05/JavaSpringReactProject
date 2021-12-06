import { useDispatch, useSelector } from "react-redux";
import { useState } from "react";
import { LoginRequest } from "../../actions/SecurityActions";
import { useHistory } from "react-router";
import classnames from "classnames";

const Login = () => {
  const [user, setUser] = useState({
    username: "",
    password: "",
    errors: {},
  });

  const dispatch = useDispatch();
  const history = useHistory();

  const { validToken } = useSelector((state) => state.security);
  if (validToken) {
    history.push("/dashboard");
  }

  const handleSubmit = async (e) => {
    e.preventDefault();

    await dispatch(LoginRequest(history, user));
  };

  const data = useSelector((State) => State.security);
  const isValid = data.validToken;

  user.errors = useSelector((state) => state.errors);

  if (isValid) {
    history.push("/dashboard");
  }

  return (
    <div className="login">
      <div className="container">
        <div className="row">
          <div className="col-md-8 m-auto">
            <h1 className="display-4 text-center">Log In</h1>
            <form onSubmit={handleSubmit}>
              <div className="form-group">
                <input
                  type="email"
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
                    "is-invalid": user.errors.username,
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
              <input type="submit" className="btn btn-info btn-block mt-4" />
            </form>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Login;
