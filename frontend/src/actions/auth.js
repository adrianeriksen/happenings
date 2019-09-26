import { createAction } from 'redux-starter-kit';

export const setPrincipal = createAction('auth/setPrincipal');

export const fetchPrincipal = () => async dispatch => {
  const response = await fetch('/api/auth/user');

  if (response.status === 200) {
    const data = await response.json();
    dispatch(setPrincipal(data));
  }
};
