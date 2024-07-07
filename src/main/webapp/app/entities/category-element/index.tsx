import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import CategoryElement from './category-element';
import CategoryElementDetail from './category-element-detail';
import CategoryElementUpdate from './category-element-update';
import CategoryElementDeleteDialog from './category-element-delete-dialog';

const CategoryElementRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<CategoryElement />} />
    <Route path="new" element={<CategoryElementUpdate />} />
    <Route path=":id">
      <Route index element={<CategoryElementDetail />} />
      <Route path="edit" element={<CategoryElementUpdate />} />
      <Route path="delete" element={<CategoryElementDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default CategoryElementRoutes;
