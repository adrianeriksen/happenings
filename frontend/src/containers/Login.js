import React from 'react';
import { connect } from 'react-redux';
import useForm from 'react-hook-form';

import { authenticate } from '../actions/auth';
import Loader from '../components/shared/Loader';
import TextInput from '../components/shared/TextInput';

function Login({ history, authenticate, isAuthenticated, isError, isLoading }) {
  if (isAuthenticated) {
    history.push('/');
  }

  const { register, handleSubmit } = useForm();

  const onSubmit = values => {
    authenticate(values.email, values.password);
  };

  if (isLoading) {
    return <Loader />;
  }

  return (
    <>
      {isError && (
        <p>You have entered an invalid username and password combination.</p>
      )}
      <form onSubmit={handleSubmit(onSubmit)}>
        <h2>Login</h2>
        <TextInput
          label="E-mail"
          id="login-form-email"
          name="email"
          type="email"
          register={register({ required: true })}
        />
        <TextInput
          label="Password"
          id="login-form-password"
          name="password"
          type="password"
          register={register({ required: true })}
        />
        <button type="submit">Log In</button>
      </form>
    </>
  );
}

const mapStateToProps = state => ({
  isAuthenticated: state.auth.isAuthenticated,
  isError: state.auth.isError,
  isLoading: state.auth.isLoading
});

const mapDispatchToProps = {
  authenticate
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Login);
