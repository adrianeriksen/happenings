import React from 'react';
import { connect } from 'react-redux';
import { Formik } from 'formik';

import { authenticate } from '../actions/auth';

function Login({ history, authenticate, isAuthenticated, isError }) {
  if (isAuthenticated) {
    history.push('/');
  }

  const initialValues = {
    email: '',
    password: ''
  };

  const validate = values => {
    const errors = {};

    if (!values.email) errors.email = 'Please enter your e-mail';
    if (!values.password) errors.password = 'Please enter your password';

    return errors;
  };

  const onSubmit = (values, { setSubmitting }) => {
    authenticate(values.email, values.password);
    setSubmitting(false);
  };

  return (
    <>
      {isError && (
        <p>You have entered an invalid username and password combination.</p>
      )}
      <Formik
        initialValues={initialValues}
        validate={validate}
        onSubmit={onSubmit}
      >
        {({
          values,
          errors,
          touched,
          handleChange,
          handleBlur,
          handleSubmit
        }) => (
          <form onSubmit={handleSubmit}>
            <h2>Login</h2>
            <label for="login-form-email">E-mail</label>
            <input
              id="login-form-email"
              type="email"
              name="email"
              value={values.email}
              onChange={handleChange}
              onBlur={handleBlur}
            />
            <label for="login-form-password">E-mail</label>
            <input
              id="login-form-password"
              type="password"
              name="password"
              value={values.password}
              onChange={handleChange}
              onBlur={handleBlur}
            />
            <button type="submit">Log In</button>
          </form>
        )}
      </Formik>
    </>
  );
}

const mapStateToProps = state => ({
  isAuthenticated: state.auth.isAuthenticated,
  isError: state.auth.isError
});

const mapDispatchToProps = {
  authenticate
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Login);
