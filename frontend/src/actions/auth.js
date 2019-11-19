import { createAction } from '@reduxjs/toolkit';

export const clearPrincipalAction = createAction('auth/clearPrincipal');

export const fetchPrincipalInitiatedAction = createAction(
  'auth/fetchPrincipalInitiated'
);
export const fetchPrincipalSuccessAction = createAction(
  'auth/fetchPrincipalSuccess'
);

export const authenticationInitiatedAction = createAction(
  'auth/authenticationInitiated'
);
export const authenticationSuccessAction = createAction(
  'auth/authenticationSuccess'
);
export const authenticationErrorAction = createAction(
  'auth/authenticationError'
);

export const fetchPrincipal = () => async dispatch => {
  dispatch(fetchPrincipalInitiatedAction());

  const response = await fetch('/api/auth/user');

  if (response.status === 200) {
    const data = await response.json();
    dispatch(fetchPrincipalSuccessAction(data));
  }
};

export const authenticate = (email, password) => dispatch => {
  dispatch(authenticationInitiatedAction());

  const headers = new Headers();
  headers.append('Content-Type', 'application/json');

  const requestOptions = {
    method: 'POST',
    headers,
    body: JSON.stringify({ email, password })
  };

  fetch('/api/auth/login', requestOptions)
    .then(res => {
      if (res.status !== 200) {
        throw Error(`Couldn't authenticate user`);
      }

      return res.json();
    })
    .then(res => dispatch(authenticationSuccessAction(res)))
    .catch(_ => dispatch(authenticationErrorAction()));
};

export const deauthenticate = () => async dispatch => {
  const headers = new Headers();
  headers.append('Content-Type', 'application/json');

  const requestOptions = {
    method: 'POST',
    headers
  };

  await fetch('/api/auth/logout', requestOptions);

  dispatch(clearPrincipalAction());
};
