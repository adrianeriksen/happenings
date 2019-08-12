import React from 'react';
import { Formik } from 'formik';

import { makeStyles } from '@material-ui/styles';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';

const useStyles = makeStyles({
  button: {
    marginTop: 24
  },
  field: {
    marginTop: 32
  }
});

function Login() {
  const classes = useStyles();

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
    setTimeout(() => {
      console.log(values);
      setSubmitting(false);
    }, 1000);
  };

  return (
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
          <TextField
            id="login-form-email"
            name="email"
            type="email"
            label="E-mail"
            onChange={handleChange}
            onBlur={handleBlur}
            value={values.email}
            error={errors.email && touched.email}
            helperText={errors.email && touched.email ? errors.email : null}
            className={classes.field}
            variant="filled"
            fullWidth
          />
          <TextField
            id="login-form-password"
            name="password"
            type="password"
            label="Password"
            onChange={handleChange}
            onBlur={handleBlur}
            value={values.password}
            error={errors.password && touched.password}
            helperText={
              errors.password && touched.password ? errors.password : null
            }
            className={classes.field}
            variant="filled"
            fullWidth
          />
          <Button
            type="submit"
            variant="contained"
            color="primary"
            size="large"
            className={classes.button}
          >
            Log In
          </Button>
        </form>
      )}
    </Formik>
  );
}

export default Login;
