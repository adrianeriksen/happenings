import React, { forwardRef } from 'react';
import { withRouter, Link } from 'react-router-dom';

import { Button } from '@material-ui/core';

const CollisionLink = forwardRef((props, ref) => (
  <Link innerRef={ref} to="/login" {...props} />
));

function LoginButton() {
  return (
    <Button color="inherit" component={CollisionLink}>
      Login
    </Button>
  );
}

export default withRouter(LoginButton);
