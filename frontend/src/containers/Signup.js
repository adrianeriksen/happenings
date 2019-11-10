import React, { useState } from 'react';
import useForm from 'react-hook-form';
import TextInput from '../components/shared/TextInput';
import Loader from '../components/shared/Loader';

function Signup({ history }) {
  const [isCreatingUser, setIsCreatingUser] = useState(false);

  const { register, handleSubmit } = useForm();

  const createUser = async values => {
    setIsCreatingUser(true);

    const headers = new Headers();
    headers.append('Content-Type', 'application/json');

    const requestOptions = {
      method: 'POST',
      headers,
      body: JSON.stringify(values)
    };

    const response = await fetch('/api/user', requestOptions);

    if (response.status === 201) {
      history.push('/');
      return;
    }

    setIsCreatingUser(false);
  };

  if (isCreatingUser) {
    return <Loader />;
  }

  return (
    <form onSubmit={handleSubmit(createUser)}>
      <h2>Create your account</h2>
      <TextInput
        label="Name"
        id="signup-form-name"
        name="name"
        type="text"
        register={register({ required: true })}
      />
      <TextInput
        label="E-mail"
        id="signup-form-email"
        name="email"
        type="email"
        register={register({ required: true })}
      />
      <TextInput
        label="Password"
        id="signup-form-password"
        name="password"
        type="password"
        register={register({ required: true })}
      />
      <button type="submit">Create Account</button>
    </form>
  );
}

export default Signup;
