import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import DraftRequestType from './draft-request-type';
import DraftRequestTypeDetail from './draft-request-type-detail';
import DraftRequestTypeUpdate from './draft-request-type-update';
import DraftRequestTypeDeleteDialog from './draft-request-type-delete-dialog';

const DraftRequestTypeRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<DraftRequestType />} />
    <Route path="new" element={<DraftRequestTypeUpdate />} />
    <Route path=":id">
      <Route index element={<DraftRequestTypeDetail />} />
      <Route path="edit" element={<DraftRequestTypeUpdate />} />
      <Route path="delete" element={<DraftRequestTypeDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default DraftRequestTypeRoutes;
