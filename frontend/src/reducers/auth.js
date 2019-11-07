import { createReducer } from 'redux-starter-kit';
import {
  setPrincipal,
  authenticationFailed,
  clearPrincipal
} from '../actions/auth';

const initialState = {
  isAuthenticated: false,
  isError: false,
  principal: null
};

export default createReducer(initialState, {
  [clearPrincipal]: () => ({
    ...initialState
  }),
  [setPrincipal]: (state, action) => ({
    ...state,
    isAuthenticated: true,
    principal: action.payload
  }),
  [authenticationFailed]: state => ({ ...state, isError: true })
});
