import React, { useContext } from 'react';
import { AppBar, Toolbar } from '@material-ui/core';
import { makeStyles } from '@material-ui/styles';

import UserContext from '../contexts/UserContext';
import UserInformation from './UserInformation';
import AnchorLink from './shared/AnchorLink';
import ButtonLink from './shared/ButtonLink';

const useStyles = makeStyles(() => ({
  root: {
    flexGrow: 1
  },
  title: {
    flexGrow: 1
  }
}));

function Header() {
  const classes = useStyles();

  const { currentUser } = useContext(UserContext);

  return (
    <div className={classes.root}>
      <AppBar position="static">
        <Toolbar>
          <AnchorLink
            to="/"
            label="Happenings"
            variant="h6"
            color="inherit"
            className={classes.title}
          />
          {currentUser ? (
            <UserInformation user={currentUser} />
          ) : (
            <ButtonLink to="/login" label="Login" color="inherit" />
          )}
        </Toolbar>
      </AppBar>
    </div>
  );
}

export default Header;
