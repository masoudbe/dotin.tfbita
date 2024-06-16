import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import DraftExtend from './draft-extend';
import DraftExtendDetail from './draft-extend-detail';
import DraftExtendUpdate from './draft-extend-update';
import DraftExtendDeleteDialog from './draft-extend-delete-dialog';

const DraftExtendRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<DraftExtend />} />
    <Route path="new" element={<DraftExtendUpdate />} />
    <Route path=":id">
      <Route index element={<DraftExtendDetail />} />
      <Route path="edit" element={<DraftExtendUpdate />} />
      <Route path="delete" element={<DraftExtendDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default DraftExtendRoutes;
