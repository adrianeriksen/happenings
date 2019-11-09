import { createAction } from 'redux-starter-kit';

export const authenticatingPrincipal = createAction(
  'auth/authenticatingPrincipal'
);
export const authenticationFailed = createAction('auth/authenticationFailed');
export const clearPrincipal = createAction('auth/clearPrincipal');
export const setPrincipal = createAction('auth/setPrincipal');

export const fetchPrincipal = () => async dispatch => {
  const response = await fetch('/api/auth/user');

  if (response.status === 200) {
    const data = await response.json();
    dispatch(setPrincipal(data));
  }
};

export const authenticate = (email, password) => async dispatch => {
  dispatch(authenticatingPrincipal());

  const headers = new Headers();
  headers.append('Content-Type', 'application/json');

  const requestOptions = {
    method: 'POST',
    headers,
    body: JSON.stringify({ email, password })
  };

  const response = await fetch('/api/auth/login', requestOptions);

  if (response.status !== 200) {
    dispatch(authenticationFailed());
    return;
  }

  const data = await response.json();
  dispatch(setPrincipal(data));
};

export const deauthenticate = () => async dispatch => {
  const headers = new Headers();
  headers.append('Content-Type', 'application/json');

  const requestOptions = {
    method: 'POST',
    headers
  };

  await fetch('/api/auth/logout', requestOptions);

  dispatch(clearPrincipal());
};
