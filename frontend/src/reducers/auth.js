import { createReducer } from 'redux-starter-kit';
import { setPrincipal, authenticationFailed } from '../actions/auth';

const initialState = {
  isAuthenticated: false,
  isError: false,
  principal: null
};

export default createReducer(initialState, {
  [setPrincipal]: (state, action) => ({
    ...state,
    isAuthenticated: true,
    principal: action.payload
  }),
  [authenticationFailed]: state => ({ ...state, isError: true })
});
